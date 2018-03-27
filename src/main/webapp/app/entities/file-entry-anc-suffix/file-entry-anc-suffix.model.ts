import { BaseEntity } from './../../shared';

export class FileEntryAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public creationTime?: any,
        public fileName?: string,
        public uploadVersions?: BaseEntity[],
        public baselines?: BaseEntity[],
    ) {
    }
}
