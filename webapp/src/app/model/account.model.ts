export interface IAccount {
   id?: string;
  firstName?: string;
  lastName?: string;
  birthday?: Date;
  email?: string;
}

export class Account implements IAccount {
  /* eslint-disable */
  constructor(
    public id?: string,
    public firstName?: string,
    public lastName?: string,
    public birthday?: Date,
    public email?: string
  ) {
  }
}
