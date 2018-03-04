import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { FileGroupAncSuffix } from './file-group-anc-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<FileGroupAncSuffix>;

@Injectable()
export class FileGroupAncSuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/file-groups';

    constructor(private http: HttpClient) { }

    create(fileGroup: FileGroupAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(fileGroup);
        return this.http.post<FileGroupAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(fileGroup: FileGroupAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(fileGroup);
        return this.http.put<FileGroupAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<FileGroupAncSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<FileGroupAncSuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<FileGroupAncSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<FileGroupAncSuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: FileGroupAncSuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<FileGroupAncSuffix[]>): HttpResponse<FileGroupAncSuffix[]> {
        const jsonResponse: FileGroupAncSuffix[] = res.body;
        const body: FileGroupAncSuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to FileGroupAncSuffix.
     */
    private convertItemFromServer(fileGroup: FileGroupAncSuffix): FileGroupAncSuffix {
        const copy: FileGroupAncSuffix = Object.assign({}, fileGroup);
        return copy;
    }

    /**
     * Convert a FileGroupAncSuffix to a JSON which can be sent to the server.
     */
    private convert(fileGroup: FileGroupAncSuffix): FileGroupAncSuffix {
        const copy: FileGroupAncSuffix = Object.assign({}, fileGroup);
        return copy;
    }
}
