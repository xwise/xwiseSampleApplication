import { BaseEntity } from './../../shared';

export const enum UploadStatus {
    'NO',
    'OK',
    'ERROR'
}

export class BaselineEntryAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public entryName?: string,
        public creationTime?: any,
        public fileName?: string,
        public blobContentType?: string,
        public blob?: any,
        public uploadStatus?: UploadStatus,
        public baselineEntryVersions?: BaseEntity[],
        public baselines?: BaseEntity[],
    ) {
    }
}
