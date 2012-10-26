CREATE TABLE InvestmentAccount (
  id bigint identity not null primary key,
  balance numeric(15, 2) not null);

CREATE TABLE Participant (
  id bigint identity not null primary key,
  investmentAccount_id bigint not null,
  bankAccountNumber character varying (20) not null,
  shares bigint not null,
  balance numeric(15, 2) not null);
