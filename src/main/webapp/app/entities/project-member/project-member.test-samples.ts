import dayjs from 'dayjs/esm';

import { IProjectMember, NewProjectMember } from './project-member.model';

export const sampleWithRequiredData: IProjectMember = {
  id: 8958,
  startDate: dayjs('2023-09-23T00:46'),
};

export const sampleWithPartialData: IProjectMember = {
  id: 15338,
  startDate: dayjs('2023-09-23T16:14'),
  endDate: dayjs('2023-09-23T06:42'),
};

export const sampleWithFullData: IProjectMember = {
  id: 11534,
  startDate: dayjs('2023-09-23T10:24'),
  endDate: dayjs('2023-09-23T03:12'),
};

export const sampleWithNewData: NewProjectMember = {
  startDate: dayjs('2023-09-23T02:03'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
