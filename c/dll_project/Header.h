
#define S11 7  
#define S12 12  
#define S13 17  
#define S14 22  
#define S21 5  
#define S22 9  
#define S23 14  
#define S24 20  
#define S31 4  
#define S32 11  
#define S33 16  
#define S34 23  
#define S41 6  
#define S42 10  
#define S43 15  
#define S44 21  

#define F(x, y, z) (((x) & (y)) | ((~x) & (z)))  
#define G(x, y, z) (((x) & (z)) | ((y) & (~z)))  
#define H(x, y, z) ((x) ^ (y) ^ (z))  
#define I(x, y, z) ((y) ^ ((x) | (~z)))  

#define ROTATE_LEFT(x, n) (((x) << (n)) | ((x) >> (32 - (n))))  

#define FF(a, b, c, d, x, s, ac) { (a) += F((b), (c), (d)) + (x) + (UINT4)(ac);  (a) = ROTATE_LEFT((a), (s)); (a) += (b);}  
#define GG(a, b, c, d, x, s, ac) { (a) += G((b), (c), (d)) + (x) + (UINT4)(ac);  (a) = ROTATE_LEFT((a), (s)); (a) += (b);}  
#define HH(a, b, c, d, x, s, ac) { (a) += H((b), (c), (d)) + (x) + (UINT4)(ac);  (a) = ROTATE_LEFT((a), (s)); (a) += (b);}  
#define II(a, b, c, d, x, s, ac) { (a) += I((b), (c), (d)) + (x) + (UINT4)(ac);  (a) = ROTATE_LEFT((a), (s)); (a) += (b);}  

typedef unsigned char* POINTER;
typedef unsigned short int UINT2;
typedef unsigned long int UINT4;

typedef struct {

    UINT4 state[4];
    UINT4 count[2];
    unsigned char buffer[64];

}  MD5_CTX;


static unsigned char PADDING[64] = {

  0x80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
  0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

};

void Encode(unsigned char* output, UINT4* input, unsigned int len);
void Decode(UINT4* output, unsigned char* input, unsigned int len);
void MD5Transform(UINT4 state[4], unsigned char block[64]);
void MD5Init(MD5_CTX* context);
void MD5Update(MD5_CTX* context, unsigned char* input, unsigned int inputLen);
void MD5Final(unsigned char digest[16], MD5_CTX* context);

void MD5Digest(char* pszInput, unsigned long nInputSize, char* pszOutPut);

// 追加字符串函数
void append(char* src, char* des, int src_len, int des_len);


// 封装MD5接口
char* MD5Encrypt(char* encrypt);
