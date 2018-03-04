import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { BaselineEntryVersionAncSuffix } from './baseline-entry-version-anc-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BaselineEntryVersionAncSuffix>;

@Injectable()
export class BaselineEntryVersionAncSuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/baseline-entry-versions';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(baselineEntryVersion: BaselineEntryVersionAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(baselineEntryVersion);
        return this.http.post<BaselineEntryVersionAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(baselineEntryVersion: BaselineEntryVersionAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(baselineEntryVersion);
        return this.http.put<BaselineEntryVersionAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BaselineEntryVersionAncSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BaselineEntryVersionAncSuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<BaselineEntryVersionAncSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BaselineEntryVersionAncSuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BaselineEntryVersionAncSuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BaselineEntryVersionAncSuffix[]>): HttpResponse<BaselineEntryVersionAncSuffix[]> {
        const jsonResponse: BaselineEntryVersionAncSuffix[] = res.body;
        const body: BaselineEntryVersionAncSuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BaselineEntryVersionAncSuffix.
     */
    private convertItemFromServer(baselineEntryVersion: BaselineEntryVersionAncSuffix): BaselineEntryVersionAncSuffix {
        const copy: BaselineEntryVersionAncSuffix = Object.assign({}, baselineEntryVersion);
        copy.creationTime = this.dateUtils
            .convertDateTimeFromServer(baselineEntryVersion.creationTime);
        return copy;
    }

    /**
     * Convert a BaselineEntryVersionAncSuffix to a JSON which can be sent to the server.
     */
    private convert(baselineEntryVersion: BaselineEntryVersionAncSuffix): BaselineEntryVersionAncSuffix {
        const copy: BaselineEntryVersionAncSuffix = Object.assign({}, baselineEntryVersion);

        copy.creationTime = this.dateUtils.toDate(baselineEntryVersion.creationTime);
        return copy;
    }
}
