const ROTATE_STRING = 'Lorem ipsum dolor sit amet';
console.log(ROTATE_STRING);
for (let i = 1; i < ROTATE_STRING.length+1; i++) {
  let printString = ROTATE_STRING.slice(-i) + ROTATE_STRING.substring(0, ROTATE_STRING.length-i);
  console.log(printString);
}
