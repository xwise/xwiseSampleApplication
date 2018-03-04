import { BaseEntity } from './../../shared';

export class BaselineEntryVersionAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public creationTime?: any,
        public version?: string,
        public baselineEntryVersions?: BaseEntity[],
    ) {
    }
}
