const faker = require("faker");

module.exports = {
  random_user: {
    firstname: faker.name.firstName(),
    lastname: faker.name.lastName(),
    email: faker.internet.email(),
    phone: faker.phone.phoneNumber(),
    fullname: `${faker.name.firstName()} ${faker.name.lastName()}`,
    city:'Томск', //todo add random data
    index: '364000',
    street:'TestStreet',
    home:'13',
    flat:'1',
    company:'TestCompany',
    inn:'123456789100'
  },
  tester_user: {
    firstname: 'testname',
    lastname: 'testlastname',
    email: 'nva.1996@yandex.ru',
    phone: '9138766597',
    fullname: 'testfullname',
    city:'Томск',
    index: '364000',
    street:'TestStreet',
    home:'13',
    flat:'1',
    company:'TestCompany',
    inn:'123456789100'
  }
}