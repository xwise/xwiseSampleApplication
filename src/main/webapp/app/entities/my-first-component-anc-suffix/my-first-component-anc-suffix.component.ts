import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { MyFirstComponentAncSuffix } from './my-first-component-anc-suffix.model';
import { MyFirstComponentAncSuffixService } from './my-first-component-anc-suffix.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-my-first-component-anc-suffix',
    templateUrl: './my-first-component-anc-suffix.component.html'
})
export class MyFirstComponentAncSuffixComponent implements OnInit, OnDestroy {
myFirstComponents: MyFirstComponentAncSuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private myFirstComponentService: MyFirstComponentAncSuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.myFirstComponentService.query().subscribe(
            (res: HttpResponse<MyFirstComponentAncSuffix[]>) => {
                this.myFirstComponents = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInMyFirstComponents();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: MyFirstComponentAncSuffix) {
        return item.id;
    }
    registerChangeInMyFirstComponents() {
        this.eventSubscriber = this.eventManager.subscribe('myFirstComponentListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
