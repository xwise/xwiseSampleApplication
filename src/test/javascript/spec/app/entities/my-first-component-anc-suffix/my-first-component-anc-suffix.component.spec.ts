/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { MyFirstComponentAncSuffixComponent } from '../../../../../../main/webapp/app/entities/my-first-component-anc-suffix/my-first-component-anc-suffix.component';
import { MyFirstComponentAncSuffixService } from '../../../../../../main/webapp/app/entities/my-first-component-anc-suffix/my-first-component-anc-suffix.service';
import { MyFirstComponentAncSuffix } from '../../../../../../main/webapp/app/entities/my-first-component-anc-suffix/my-first-component-anc-suffix.model';

describe('Component Tests', () => {

    describe('MyFirstComponentAncSuffix Management Component', () => {
        let comp: MyFirstComponentAncSuffixComponent;
        let fixture: ComponentFixture<MyFirstComponentAncSuffixComponent>;
        let service: MyFirstComponentAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [MyFirstComponentAncSuffixComponent],
                providers: [
                    MyFirstComponentAncSuffixService
                ]
            })
            .overrideTemplate(MyFirstComponentAncSuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MyFirstComponentAncSuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MyFirstComponentAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MyFirstComponentAncSuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.myFirstComponents[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
