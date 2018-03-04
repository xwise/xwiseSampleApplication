import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { FileGroupAncSuffix } from './file-group-anc-suffix.model';
import { FileGroupAncSuffixService } from './file-group-anc-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-file-group-anc-suffix',
    templateUrl: './file-group-anc-suffix.component.html'
})
export class FileGroupAncSuffixComponent implements OnInit, OnDestroy {
fileGroups: FileGroupAncSuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private fileGroupService: FileGroupAncSuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.fileGroupService.query().subscribe(
            (res: HttpResponse<FileGroupAncSuffix[]>) => {
                this.fileGroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInFileGroups();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: FileGroupAncSuffix) {
        return item.id;
    }
    registerChangeInFileGroups() {
        this.eventSubscriber = this.eventManager.subscribe('fileGroupListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
