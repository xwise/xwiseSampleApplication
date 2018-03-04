import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { FileEntryAncSuffix } from './file-entry-anc-suffix.model';
import { FileEntryAncSuffixService } from './file-entry-anc-suffix.service';

@Injectable()
export class FileEntryAncSuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fileEntryService: FileEntryAncSuffixService

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
                this.fileEntryService.find(id)
                    .subscribe((fileEntryResponse: HttpResponse<FileEntryAncSuffix>) => {
                        const fileEntry: FileEntryAncSuffix = fileEntryResponse.body;
                        fileEntry.creationTime = this.datePipe
                            .transform(fileEntry.creationTime, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.fileEntryModalRef(component, fileEntry);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.fileEntryModalRef(component, new FileEntryAncSuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    fileEntryModalRef(component: Component, fileEntry: FileEntryAncSuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fileEntry = fileEntry;
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
