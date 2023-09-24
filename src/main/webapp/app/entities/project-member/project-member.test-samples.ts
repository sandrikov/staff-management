import dayjs from 'dayjs/esm';

import { IProjectMember, NewProjectMember } from './project-member.model';

export const sampleWithRequiredData: IProjectMember = {
  id: 8958,
  startDate: dayjs('2023-09-23T22:08'),
};

export const sampleWithPartialData: IProjectMember = {
  id: 15338,
  startDate: dayjs('2023-09-24T13:36'),
  endDate: dayjs('2023-09-24T04:04'),
};

export const sampleWithFullData: IProjectMember = {
  id: 11534,
  startDate: dayjs('2023-09-24T07:46'),
  endDate: dayjs('2023-09-24T00:34'),
};

export const sampleWithNewData: NewProjectMember = {
  startDate: dayjs('2023-09-23T23:25'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
