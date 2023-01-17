// In single-spa, the assets need to be loaded from a dynamic location,
// instead of hard coded to `/assets`. We use webpack public path for this.
// See https://webpack.js.org/guides/public-path/#root

export const spaSettings: { baseUrl: string | undefined } = {
  baseUrl: undefined,
};

export const assetUrl = (url: string): string => {
  const publicPath = spaSettings.baseUrl ? spaSettings.baseUrl : '';
  const publicPathSuffix = publicPath.endsWith('/') ? '' : '/';
  const urlPrefix = url.startsWith('/') ? '' : '/';

  return `${publicPath}${publicPathSuffix}assets${urlPrefix}${url}`;
};
