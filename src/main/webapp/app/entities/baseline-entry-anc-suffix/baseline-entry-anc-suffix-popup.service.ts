import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { BaselineEntryAncSuffix } from './baseline-entry-anc-suffix.model';
import { BaselineEntryAncSuffixService } from './baseline-entry-anc-suffix.service';

@Injectable()
export class BaselineEntryAncSuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private baselineEntryService: BaselineEntryAncSuffixService

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
                this.baselineEntryService.find(id)
                    .subscribe((baselineEntryResponse: HttpResponse<BaselineEntryAncSuffix>) => {
                        const baselineEntry: BaselineEntryAncSuffix = baselineEntryResponse.body;
                        baselineEntry.creationTime = this.datePipe
                            .transform(baselineEntry.creationTime, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.baselineEntryModalRef(component, baselineEntry);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.baselineEntryModalRef(component, new BaselineEntryAncSuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    baselineEntryModalRef(component: Component, baselineEntry: BaselineEntryAncSuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.baselineEntry = baselineEntry;
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
