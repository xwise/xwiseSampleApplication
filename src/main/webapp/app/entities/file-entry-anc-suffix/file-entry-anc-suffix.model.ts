import { BaseEntity } from './../../shared';

export class FileEntryAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public entryName?: string,
        public baselineEntryVersions?: BaseEntity[],
        public baselines?: BaseEntity[],
    ) {
    }
}
