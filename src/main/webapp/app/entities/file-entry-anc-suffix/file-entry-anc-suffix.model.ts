import { BaseEntity } from './../../shared';

export const enum UploadStatus {
    'NO',
    'OK',
    'ERROR'
}

export class FileEntryAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public creationTime?: any,
        public fileNameVersion?: string,
        public fileName?: string,
        public version?: string,
        public fileContentType?: string,
        public file?: any,
        public uploadStatus?: UploadStatus,
        public baselines?: BaseEntity[],
    ) {
    }
}
