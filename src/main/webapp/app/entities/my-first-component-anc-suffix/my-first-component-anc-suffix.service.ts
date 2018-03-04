import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { MyFirstComponentAncSuffix } from './my-first-component-anc-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MyFirstComponentAncSuffix>;

@Injectable()
export class MyFirstComponentAncSuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/my-first-components';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(myFirstComponent: MyFirstComponentAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(myFirstComponent);
        return this.http.post<MyFirstComponentAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(myFirstComponent: MyFirstComponentAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(myFirstComponent);
        return this.http.put<MyFirstComponentAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MyFirstComponentAncSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MyFirstComponentAncSuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<MyFirstComponentAncSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MyFirstComponentAncSuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MyFirstComponentAncSuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MyFirstComponentAncSuffix[]>): HttpResponse<MyFirstComponentAncSuffix[]> {
        const jsonResponse: MyFirstComponentAncSuffix[] = res.body;
        const body: MyFirstComponentAncSuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MyFirstComponentAncSuffix.
     */
    private convertItemFromServer(myFirstComponent: MyFirstComponentAncSuffix): MyFirstComponentAncSuffix {
        const copy: MyFirstComponentAncSuffix = Object.assign({}, myFirstComponent);
        copy.creationTime = this.dateUtils
            .convertDateTimeFromServer(myFirstComponent.creationTime);
        return copy;
    }

    /**
     * Convert a MyFirstComponentAncSuffix to a JSON which can be sent to the server.
     */
    private convert(myFirstComponent: MyFirstComponentAncSuffix): MyFirstComponentAncSuffix {
        const copy: MyFirstComponentAncSuffix = Object.assign({}, myFirstComponent);

        copy.creationTime = this.dateUtils.toDate(myFirstComponent.creationTime);
        return copy;
    }
}
