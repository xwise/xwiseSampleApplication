import { BaseEntity } from './../../shared';

export class FileGroupAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public groupName?: string,
        public fileEntries?: BaseEntity[],
        public baselines?: BaseEntity[],
    ) {
    }
}
