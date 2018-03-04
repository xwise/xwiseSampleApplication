import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { BaselineAncSuffix } from './baseline-anc-suffix.model';
import { BaselineAncSuffixService } from './baseline-anc-suffix.service';

@Injectable()
export class BaselineAncSuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private baselineService: BaselineAncSuffixService

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
                this.baselineService.find(id)
                    .subscribe((baselineResponse: HttpResponse<BaselineAncSuffix>) => {
                        const baseline: BaselineAncSuffix = baselineResponse.body;
                        baseline.creationTime = this.datePipe
                            .transform(baseline.creationTime, 'yyyy-MM-ddTHH:mm:ss');
                        baseline.milestone = this.datePipe
                            .transform(baseline.milestone, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.baselineModalRef(component, baseline);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.baselineModalRef(component, new BaselineAncSuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    baselineModalRef(component: Component, baseline: BaselineAncSuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.baseline = baseline;
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
