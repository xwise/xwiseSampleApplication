import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { UploadVersionAncSuffix } from './upload-version-anc-suffix.model';
import { UploadVersionAncSuffixService } from './upload-version-anc-suffix.service';

@Injectable()
export class UploadVersionAncSuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private uploadVersionService: UploadVersionAncSuffixService

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
                this.uploadVersionService.find(id)
                    .subscribe((uploadVersionResponse: HttpResponse<UploadVersionAncSuffix>) => {
                        const uploadVersion: UploadVersionAncSuffix = uploadVersionResponse.body;
                        this.ngbModalRef = this.uploadVersionModalRef(component, uploadVersion);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.uploadVersionModalRef(component, new UploadVersionAncSuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    uploadVersionModalRef(component: Component, uploadVersion: UploadVersionAncSuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.uploadVersion = uploadVersion;
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
