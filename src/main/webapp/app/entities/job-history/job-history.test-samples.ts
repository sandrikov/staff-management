import dayjs from 'dayjs/esm';

import { IJobHistory, NewJobHistory } from './job-history.model';

export const sampleWithRequiredData: IJobHistory = {
  id: 3372,
  startDate: dayjs('2023-09-24T16:40'),
};

export const sampleWithPartialData: IJobHistory = {
  id: 26251,
  startDate: dayjs('2023-09-24T09:06'),
  endDate: dayjs('2023-09-24T13:31'),
};

export const sampleWithFullData: IJobHistory = {
  id: 10739,
  startDate: dayjs('2023-09-24T02:11'),
  endDate: dayjs('2023-09-24T06:37'),
};

export const sampleWithNewData: NewJobHistory = {
  startDate: dayjs('2023-09-24T16:43'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
