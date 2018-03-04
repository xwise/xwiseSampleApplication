import { BaseEntity } from './../../shared';

export const enum UploadStatus {
    'NO',
    'OK',
    'ERROR'
}

export class BaselineEntryVersionAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public creationTime?: any,
        public version?: string,
        public fileName?: string,
        public blobContentType?: string,
        public blob?: any,
        public uploadStatus?: UploadStatus,
        public fileEntries?: BaseEntity[],
    ) {
    }
}
