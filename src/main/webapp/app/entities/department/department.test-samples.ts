import { IDepartment, NewDepartment } from './department.model';

export const sampleWithRequiredData: IDepartment = {
  id: 4864,
  departmentName: 'whoa bounce',
};

export const sampleWithPartialData: IDepartment = {
  id: 32334,
  departmentName: 'pouch hmph',
};

export const sampleWithFullData: IDepartment = {
  id: 25966,
  departmentName: 'quicker aha',
};

export const sampleWithNewData: NewDepartment = {
  departmentName: 'searchingly clear',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
