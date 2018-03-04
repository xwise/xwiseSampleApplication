import { BaseEntity } from './../../shared';

export const enum BaselineStatus {
    'ACTIVE',
    'CLOSED'
}

export class BaselineAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public creationTime?: any,
        public baselineName?: string,
        public milestone?: any,
        public status?: BaselineStatus,
        public fileGroups?: BaseEntity[],
    ) {
    }
}
