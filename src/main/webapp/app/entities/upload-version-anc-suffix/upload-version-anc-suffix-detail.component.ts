import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { UploadVersionAncSuffix } from './upload-version-anc-suffix.model';
import { UploadVersionAncSuffixService } from './upload-version-anc-suffix.service';

@Component({
    selector: 'jhi-upload-version-anc-suffix-detail',
    templateUrl: './upload-version-anc-suffix-detail.component.html'
})
export class UploadVersionAncSuffixDetailComponent implements OnInit, OnDestroy {

    uploadVersion: UploadVersionAncSuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private uploadVersionService: UploadVersionAncSuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUploadVersions();
    }

    load(id) {
        this.uploadVersionService.find(id)
            .subscribe((uploadVersionResponse: HttpResponse<UploadVersionAncSuffix>) => {
                this.uploadVersion = uploadVersionResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUploadVersions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'uploadVersionListModification',
            (response) => this.load(this.uploadVersion.id)
        );
    }
}
