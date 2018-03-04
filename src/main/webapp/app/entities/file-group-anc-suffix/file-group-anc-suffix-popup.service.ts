import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { FileGroupAncSuffix } from './file-group-anc-suffix.model';
import { FileGroupAncSuffixService } from './file-group-anc-suffix.service';

@Injectable()
export class FileGroupAncSuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private fileGroupService: FileGroupAncSuffixService

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
                this.fileGroupService.find(id)
                    .subscribe((fileGroupResponse: HttpResponse<FileGroupAncSuffix>) => {
                        const fileGroup: FileGroupAncSuffix = fileGroupResponse.body;
                        this.ngbModalRef = this.fileGroupModalRef(component, fileGroup);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.fileGroupModalRef(component, new FileGroupAncSuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    fileGroupModalRef(component: Component, fileGroup: FileGroupAncSuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fileGroup = fileGroup;
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
