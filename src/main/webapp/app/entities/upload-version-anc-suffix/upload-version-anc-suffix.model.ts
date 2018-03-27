import { BaseEntity } from './../../shared';

export const enum UploadStatus {
    'NO',
    'OK',
    'ERROR'
}

export class UploadVersionAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public version?: string,
        public fileContentType?: string,
        public file?: any,
        public uploadStatus?: UploadStatus,
        public fileEntryFileName?: string,
        public fileEntryId?: number,
    ) {
    }
}
