import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UploadVersionAncSuffix } from './upload-version-anc-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UploadVersionAncSuffix>;

@Injectable()
export class UploadVersionAncSuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/upload-versions';

    constructor(private http: HttpClient) { }

    create(uploadVersion: UploadVersionAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(uploadVersion);
        return this.http.post<UploadVersionAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(uploadVersion: UploadVersionAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(uploadVersion);
        return this.http.put<UploadVersionAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UploadVersionAncSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UploadVersionAncSuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<UploadVersionAncSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UploadVersionAncSuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UploadVersionAncSuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UploadVersionAncSuffix[]>): HttpResponse<UploadVersionAncSuffix[]> {
        const jsonResponse: UploadVersionAncSuffix[] = res.body;
        const body: UploadVersionAncSuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UploadVersionAncSuffix.
     */
    private convertItemFromServer(uploadVersion: UploadVersionAncSuffix): UploadVersionAncSuffix {
        const copy: UploadVersionAncSuffix = Object.assign({}, uploadVersion);
        return copy;
    }

    /**
     * Convert a UploadVersionAncSuffix to a JSON which can be sent to the server.
     */
    private convert(uploadVersion: UploadVersionAncSuffix): UploadVersionAncSuffix {
        const copy: UploadVersionAncSuffix = Object.assign({}, uploadVersion);
        return copy;
    }
}
