import { IRole, NewRole } from './role.model';

export const sampleWithRequiredData: IRole = {
  id: 5116,
  roleName: 'who steeple',
};

export const sampleWithPartialData: IRole = {
  id: 6684,
  roleName: 'even',
};

export const sampleWithFullData: IRole = {
  id: 22793,
  roleName: 'geez suppression step',
};

export const sampleWithNewData: NewRole = {
  roleName: 'aw',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
