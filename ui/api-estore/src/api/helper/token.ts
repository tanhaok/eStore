const IGNORE_AUTH_URL = ['auth/login', 'auth/register', 'auth/active-account', 'auth/refreshtoken'];

const isIgnore = (url: string | undefined) => {
  if (url === undefined || url === '') {
    return true;
  }
  IGNORE_AUTH_URL.map((item) => {
    if (item.indexOf(url) > 0) {
      return false;
    }
  });
  return true;
};

const saveAccessToken = (accessToken: string) => {
  localStorage.setItem('access_token', accessToken);
};

const getAccessToken = () => {
  return localStorage.getItem('access_token');
};

const getRefreshToken = () => {
  return localStorage.getItem('refresh_token');
};

const saveRefreshToken = (refreshToken: string) => {
  localStorage.setItem('refresh_token', refreshToken);
};

const updateAccessToken = (accessToken: string) => {
  localStorage.removeItem('access_token');
  localStorage.setItem('access_token', accessToken);
};

export {
  getAccessToken,
  saveAccessToken,
  updateAccessToken,
  isIgnore,
  getRefreshToken,
  saveRefreshToken,
};
