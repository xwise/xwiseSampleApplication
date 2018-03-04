import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { BaselineEntryVersionAncSuffix } from './baseline-entry-version-anc-suffix.model';
import { BaselineEntryVersionAncSuffixService } from './baseline-entry-version-anc-suffix.service';

@Injectable()
export class BaselineEntryVersionAncSuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private baselineEntryVersionService: BaselineEntryVersionAncSuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.baselineEntryVersionService.find(id)
                    .subscribe((baselineEntryVersionResponse: HttpResponse<BaselineEntryVersionAncSuffix>) => {
                        const baselineEntryVersion: BaselineEntryVersionAncSuffix = baselineEntryVersionResponse.body;
                        baselineEntryVersion.creationTime = this.datePipe
                            .transform(baselineEntryVersion.creationTime, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.baselineEntryVersionModalRef(component, baselineEntryVersion);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.baselineEntryVersionModalRef(component, new BaselineEntryVersionAncSuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    baselineEntryVersionModalRef(component: Component, baselineEntryVersion: BaselineEntryVersionAncSuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.baselineEntryVersion = baselineEntryVersion;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
