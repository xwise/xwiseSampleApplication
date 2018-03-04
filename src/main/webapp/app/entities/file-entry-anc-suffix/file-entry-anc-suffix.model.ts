import { BaseEntity } from './../../shared';

export class FileEntryAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public creationTime?: any,
        public fileName?: string,
        public uploadVersionVersion?: string,
        public uploadVersionId?: number,
        public baselines?: BaseEntity[],
    ) {
    }
}
