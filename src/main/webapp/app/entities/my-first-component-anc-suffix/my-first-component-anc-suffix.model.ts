import { BaseEntity } from './../../shared';

export class MyFirstComponentAncSuffix implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public creationTime?: any,
        public attribute1?: string,
        public attribute2?: string,
        public attribute3?: string,
        public attribute4?: string,
        public attribute5?: string,
        public attribute6?: string,
        public attribute7?: string,
    ) {
    }
}
