import dayjs from 'dayjs/esm';

import { IJobHistory, NewJobHistory } from './job-history.model';

export const sampleWithRequiredData: IJobHistory = {
  id: 3372,
  startDate: dayjs('2023-09-23T19:18'),
};

export const sampleWithPartialData: IJobHistory = {
  id: 26251,
  startDate: dayjs('2023-09-23T11:45'),
  endDate: dayjs('2023-09-23T16:09'),
};

export const sampleWithFullData: IJobHistory = {
  id: 10739,
  startDate: dayjs('2023-09-23T04:49'),
  endDate: dayjs('2023-09-23T09:15'),
};

export const sampleWithNewData: NewJobHistory = {
  startDate: dayjs('2023-09-23T19:22'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
