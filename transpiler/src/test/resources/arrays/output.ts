export class DoubleArray {
  public arrayD: Array<Float64Array> = new Array<Float64Array>(1);
  public arrayI: Array<Int32Array>;
  public arrayD2: Array<Array<Array<Float64Array>>> = new Array<Array<Array<Float64Array>>>(10);
  private _values: Array<Float64Array>;
  public c: any;
  constructor() {
    this._values = new Array<Float64Array>(10);
    for(let _values_d1 = 0; _values_d1 < 10; _values_d1++){
      this._values[_values_d1] = new Float64Array(10);
    };
  }
  public set(): void {
    let d: number = java.lang.Double.POSITIVE_INFINITY;
    let d2: number = java.lang.Double.NEGATIVE_INFINITY;
    let centroids: Array<Float64Array> = new Array<Float64Array>(1);
    for(let centroids_d1 = 0; centroids_d1 < 1; centroids_d1++){
      centroids[centroids_d1] = new Float64Array(2);
    };
    this.arrayD = new Array<Float64Array>(1);
    for(let arrayD_d1 = 0; arrayD_d1 < 1; arrayD_d1++){
      this.arrayD[arrayD_d1] = new Float64Array(2);
    };
    this.arrayI = new Array<Int32Array>(1);
    this.arrayD2 = new Array<Array<Array<Float64Array>>>(20);
    for(let arrayD2_d1 = 0; arrayD2_d1 < 20; arrayD2_d1++){
      this.arrayD2[arrayD2_d1] = new Array<Array<Float64Array>>(19);
      for(let arrayD2_d2 = 0; arrayD2_d2 < 19; arrayD2_d2++){
        this.arrayD2[arrayD2_d1][arrayD2_d2] = new Array<Float64Array>(18);
        for(let arrayD2_d3 = 0; arrayD2_d3 < 18; arrayD2_d3++){
          this.arrayD2[arrayD2_d1][arrayD2_d2][arrayD2_d3] = new Float64Array(17);
        }
      }
    };
    let _back_colors: Array<boolean> = new Array<boolean>(10);
  }
  public sortTest(): void {
    let arr: Int32Array = new Int32Array([0, 1, 2, 3, 4]);
    arr.sort();
    for (let i: number = 0; i < 10; i++) {
      junit.Assert.assertEquals(2.,3.,1.);
    }
  }
}