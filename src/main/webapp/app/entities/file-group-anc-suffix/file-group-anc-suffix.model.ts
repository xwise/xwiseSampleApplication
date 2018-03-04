import { BaseEntity } from './../../shared';

export class FileGroupAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public groupName?: string,
        public baselines?: BaseEntity[],
        public fileEntries?: BaseEntity[],
    ) {
    }
}
