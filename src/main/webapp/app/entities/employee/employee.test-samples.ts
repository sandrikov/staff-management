import dayjs from 'dayjs/esm';

import { IEmployee, NewEmployee } from './employee.model';

export const sampleWithRequiredData: IEmployee = {
  id: 8804,
  firstName: 'Loma',
  lastName: 'Harvey-Steuber',
};

export const sampleWithPartialData: IEmployee = {
  id: 7797,
  firstName: 'Osvaldo',
  lastName: 'Bayer',
  email: 'Avery.Yost@gmail.com',
  phoneNumber: 'rash',
  username: 'after douse minus',
  hireDate: dayjs('2023-09-24T06:20'),
};

export const sampleWithFullData: IEmployee = {
  id: 11304,
  firstName: 'Neil',
  lastName: 'Osinski',
  email: 'Cydney_Johnson@yahoo.com',
  phoneNumber: 'wherever fulfil bucket',
  username: 'whenever',
  hireDate: dayjs('2023-09-24T00:49'),
  salary: 17051,
};

export const sampleWithNewData: NewEmployee = {
  firstName: 'Kylie',
  lastName: 'Beier',
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
