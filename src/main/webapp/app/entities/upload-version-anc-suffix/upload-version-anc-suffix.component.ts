import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UploadVersionAncSuffix } from './upload-version-anc-suffix.model';
import { UploadVersionAncSuffixService } from './upload-version-anc-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-upload-version-anc-suffix',
    templateUrl: './upload-version-anc-suffix.component.html'
})
export class UploadVersionAncSuffixComponent implements OnInit, OnDestroy {
uploadVersions: UploadVersionAncSuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private uploadVersionService: UploadVersionAncSuffixService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.uploadVersionService.query().subscribe(
            (res: HttpResponse<UploadVersionAncSuffix[]>) => {
                this.uploadVersions = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUploadVersions();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UploadVersionAncSuffix) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    registerChangeInUploadVersions() {
        this.eventSubscriber = this.eventManager.subscribe('uploadVersionListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
