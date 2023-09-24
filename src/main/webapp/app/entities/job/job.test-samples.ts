import { IJob, NewJob } from './job.model';

export const sampleWithRequiredData: IJob = {
  id: 28902,
  jobTitle: 'National Operations Designer',
};

export const sampleWithPartialData: IJob = {
  id: 28086,
  jobTitle: 'Senior Program Liaison',
};

export const sampleWithFullData: IJob = {
  id: 4374,
  jobTitle: 'Principal Research Technician',
};

export const sampleWithNewData: NewJob = {
  jobTitle: 'Lead Web Producer',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
