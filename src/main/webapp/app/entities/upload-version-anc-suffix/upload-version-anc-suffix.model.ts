import { BaseEntity } from './../../shared';

export class UploadVersionAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public version?: string,
        public fileContentType?: string,
        public file?: any,
        public fileEntryFileName?: string,
        public fileEntryId?: number,
    ) {
    }
}
