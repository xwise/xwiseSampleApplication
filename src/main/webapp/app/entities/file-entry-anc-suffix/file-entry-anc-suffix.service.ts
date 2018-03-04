import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { FileEntryAncSuffix } from './file-entry-anc-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<FileEntryAncSuffix>;

@Injectable()
export class FileEntryAncSuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/file-entries';

    constructor(private http: HttpClient) { }

    create(fileEntry: FileEntryAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(fileEntry);
        return this.http.post<FileEntryAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(fileEntry: FileEntryAncSuffix): Observable<EntityResponseType> {
        const copy = this.convert(fileEntry);
        return this.http.put<FileEntryAncSuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<FileEntryAncSuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<FileEntryAncSuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<FileEntryAncSuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<FileEntryAncSuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: FileEntryAncSuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<FileEntryAncSuffix[]>): HttpResponse<FileEntryAncSuffix[]> {
        const jsonResponse: FileEntryAncSuffix[] = res.body;
        const body: FileEntryAncSuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to FileEntryAncSuffix.
     */
    private convertItemFromServer(fileEntry: FileEntryAncSuffix): FileEntryAncSuffix {
        const copy: FileEntryAncSuffix = Object.assign({}, fileEntry);
        return copy;
    }

    /**
     * Convert a FileEntryAncSuffix to a JSON which can be sent to the server.
     */
    private convert(fileEntry: FileEntryAncSuffix): FileEntryAncSuffix {
        const copy: FileEntryAncSuffix = Object.assign({}, fileEntry);
        return copy;
    }
}
