/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { XwiseSampleApplicationTestModule } from '../../../test.module';
import { MyFirstComponentAncSuffixDetailComponent } from '../../../../../../main/webapp/app/entities/my-first-component-anc-suffix/my-first-component-anc-suffix-detail.component';
import { MyFirstComponentAncSuffixService } from '../../../../../../main/webapp/app/entities/my-first-component-anc-suffix/my-first-component-anc-suffix.service';
import { MyFirstComponentAncSuffix } from '../../../../../../main/webapp/app/entities/my-first-component-anc-suffix/my-first-component-anc-suffix.model';

describe('Component Tests', () => {

    describe('MyFirstComponentAncSuffix Management Detail Component', () => {
        let comp: MyFirstComponentAncSuffixDetailComponent;
        let fixture: ComponentFixture<MyFirstComponentAncSuffixDetailComponent>;
        let service: MyFirstComponentAncSuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [XwiseSampleApplicationTestModule],
                declarations: [MyFirstComponentAncSuffixDetailComponent],
                providers: [
                    MyFirstComponentAncSuffixService
                ]
            })
            .overrideTemplate(MyFirstComponentAncSuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MyFirstComponentAncSuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MyFirstComponentAncSuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MyFirstComponentAncSuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.myFirstComponent).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
