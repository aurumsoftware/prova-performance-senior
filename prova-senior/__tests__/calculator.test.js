const CalculatorModule = require('../Calculator.js');

describe('Calculator', () => {
  let calculator;

  beforeEach(() => {
     calculator = new CalculatorModule();
  });

  it('test', () => {
    const oldPrice = 100;
    const newPrice = 300;
    const dueDate = '2025-04-15';
    const recurrencyType = 'MONTHLY';
    const result = calculator.calculateProportionalDifferencePrice(oldPrice, newPrice, dueDate, recurrencyType);
    expect(83.87096774193549).toBe(result);
  })
});