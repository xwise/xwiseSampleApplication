import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { FileGroupAncSuffix } from './file-group-anc-suffix.model';
import { FileGroupAncSuffixService } from './file-group-anc-suffix.service';

@Component({
    selector: 'jhi-file-group-anc-suffix-detail',
    templateUrl: './file-group-anc-suffix-detail.component.html'
})
export class FileGroupAncSuffixDetailComponent implements OnInit, OnDestroy {

    fileGroup: FileGroupAncSuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private fileGroupService: FileGroupAncSuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFileGroups();
    }

    load(id) {
        this.fileGroupService.find(id)
            .subscribe((fileGroupResponse: HttpResponse<FileGroupAncSuffix>) => {
                this.fileGroup = fileGroupResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFileGroups() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fileGroupListModification',
            (response) => this.load(this.fileGroup.id)
        );
    }
}
