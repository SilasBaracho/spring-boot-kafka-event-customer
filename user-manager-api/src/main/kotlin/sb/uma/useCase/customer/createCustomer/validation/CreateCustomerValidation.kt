package sb.uma.useCase.customer.createCustomer.validation

import sb.uma.useCase.customer.createCustomer.CreateCustomerInput
import sb.uma.util.Validation

interface CreateCustomerValidation: Validation<CreateCustomerInput> {}