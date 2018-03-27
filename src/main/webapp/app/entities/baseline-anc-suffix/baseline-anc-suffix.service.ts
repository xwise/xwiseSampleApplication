import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { BaselineAncSuffix } from './baseline-anc-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BaselineAncSuffix>;

@Injectable()
export class BaselineAncSuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/baselines';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(baseline: BaselineAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(baseline);
        return this.http.post<BaselineAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(baseline: BaselineAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(baseline);
        return this.http.put<BaselineAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BaselineAncSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BaselineAncSuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<BaselineAncSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BaselineAncSuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BaselineAncSuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BaselineAncSuffix[]>): HttpResponse<BaselineAncSuffix[]> {
        const jsonResponse: BaselineAncSuffix[] = res.body;
        const body: BaselineAncSuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BaselineAncSuffix.
     */
    private convertItemFromServer(baseline: BaselineAncSuffix): BaselineAncSuffix {
        const copy: BaselineAncSuffix = Object.assign({}, baseline);
        copy.creationTime = this.dateUtils
            .convertDateTimeFromServer(baseline.creationTime);
        copy.milestone = this.dateUtils
            .convertDateTimeFromServer(baseline.milestone);
        return copy;
    }

    /**
     * Convert a BaselineAncSuffix to a JSON which can be sent to the server.
     */
    private convert(baseline: BaselineAncSuffix): BaselineAncSuffix {
        const copy: BaselineAncSuffix = Object.assign({}, baseline);

        copy.creationTime = this.dateUtils.toDate(baseline.creationTime);

        copy.milestone = this.dateUtils.toDate(baseline.milestone);
        return copy;
    }
}
