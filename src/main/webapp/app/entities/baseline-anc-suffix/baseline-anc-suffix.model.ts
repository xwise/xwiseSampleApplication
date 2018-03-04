import { BaseEntity } from './../../shared';

export const enum BaselineStatus {
    'ACTIVE',
    'CLOSED'
}

export class BaselineAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public baselineName?: string,
        public creationTime?: any,
        public milestone?: any,
        public status?: BaselineStatus,
        public baselineEntries?: BaseEntity[],
    ) {
    }
}
