import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MyFirstComponentAncSuffix } from './my-first-component-anc-suffix.model';
import { MyFirstComponentAncSuffixService } from './my-first-component-anc-suffix.service';

@Component({
    selector: 'jhi-my-first-component-anc-suffix-detail',
    templateUrl: './my-first-component-anc-suffix-detail.component.html'
})
export class MyFirstComponentAncSuffixDetailComponent implements OnInit, OnDestroy {

    myFirstComponent: MyFirstComponentAncSuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private myFirstComponentService: MyFirstComponentAncSuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMyFirstComponents();
    }

    load(id) {
        this.myFirstComponentService.find(id)
            .subscribe((myFirstComponentResponse: HttpResponse<MyFirstComponentAncSuffix>) => {
                this.myFirstComponent = myFirstComponentResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMyFirstComponents() {
        this.eventSubscriber = this.eventManager.subscribe(
            'myFirstComponentListModification',
            (response) => this.load(this.myFirstComponent.id)
        );
    }
}
