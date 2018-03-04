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
        public version?: string,
        public fileName?: string,
        public fileContentType?: string,
        public file?: any,
        public uploadStatus?: UploadStatus,
        public fileGroups?: BaseEntity[],
    ) {
    }
}
