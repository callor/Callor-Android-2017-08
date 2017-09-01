package com.callor.navermap;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapCompassManager;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapLocationManager;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapMyLocationOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

public class MainActivity extends NMapActivity {

    private final String CLIENT_ID = "4fYPdWh0cG6BquXjQ4cp";
    private NMapView mMapView ;
    private NMapController mMapController ;

    // 동경 약 126, 북위 35도
    private static final NGeoPoint NMAP_LOCATION_DEFAULT
            = new NGeoPoint(126.860271,35.130971) ;

    // 지도 표시방법에 대한 세팅
    private static final int NMAP_ZOOMLEVEL_DEFAULT = 11 ; // ZoomLevel : 최대 14배
    private static final boolean NMAP_TRAFFIC_MODE_DEFALT = false ;
    private static final boolean NMAP_BICYCLE_MODE_DEFAULT = false;
    private static final int NMAP_VIEW_MODE_DEFAULT = NMapView.VIEW_MODE_VECTOR;

    private static final String KEY_ZOOM_LEVEL = "NMapViewer.zoomLevel";
    private static final String KEY_CENTER_LONGITUDE = "NMapViewer.centerLongitudeE6";
    private static final String KEY_CENTER_LATITUDE = "NMapViewer.centerlatitudeE6";
    private static final String KEY_VIEW_MODE = "NMapViewer.viewMode";
    private static final String KEY_TRAFIC_MODE = "NMapViewer.trafficMode";
    private static final String KEY_BICYCLE_MODE = "NMapViewer.bicycleMode";

    private NMapOverlayManager mOverlayManager;

    private NMapMyLocationOverlay mMapMyLocationOverlay;
    private NMapLocationManager mMapLocationManager;
    private NMapCompassManager mMapCommpassManager;

    private NMapResourceProvider mMapViewerResourceProvider;
    private NMapPOIdataOverlay mMapPOIdataOverlay;
    private NMapPOIitem mMapPOIitem ;

    private static boolean USE_XML_LAYOUT = false;


//    private NMapContainerView mapContainerView ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mMapView = new NMapView(this);
        setContentView(mMapView);
        mMapView.setClientId(CLIENT_ID);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();

        // 지도 설정을 양보
        mMapController = mMapView.getMapController();
        mMapController.setMapCenter(NMAP_LOCATION_DEFAULT);
        mMapController.setZoomLevel(NMAP_ZOOMLEVEL_DEFAULT);

        NMapView.LayoutParams lp
                = new NMapView.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        NMapView.LayoutParams.BOTTOM_RIGHT);

        // 지도 zoom 컨트롤러 실행
        mMapView.setBuiltInZoomControls(true,lp);

        // 네이버 지앱 바로가기 버튼튼
       mMapView.setBuiltInAppControl(true);

        // 지도위에 각종 정보를 박스에 표시하기위한 방법
        // 아래 메서드들을 설정하면
        mMapViewerResourceProvider = new NMapResourceProvider(this) {
            @Override
            protected int findResourceIdForMarker(int i, boolean b) {
                return 0;
            }

            @Override
            protected Drawable getDrawableForMarker(int i, boolean b, NMapOverlayItem nMapOverlayItem) {
                return null;
            }

            @Override
            public Drawable getCalloutBackground(NMapOverlayItem nMapOverlayItem) {
                return null;
            }

            @Override
            public String getCalloutRightButtonText(NMapOverlayItem nMapOverlayItem) {
                return null;
            }

            @Override
            public Drawable[] getCalloutRightButton(NMapOverlayItem nMapOverlayItem) {
                return new Drawable[0];
            }

            @Override
            public Drawable[] getCalloutRightAccessory(NMapOverlayItem nMapOverlayItem) {
                return new Drawable[0];
            }

            @Override
            public int[] getCalloutTextColors(NMapOverlayItem nMapOverlayItem) {
                return new int[0];
            }

            @Override
            public Drawable[] getLocationDot() {
                return new Drawable[0];
            }

            @Override
            public Drawable getDirectionArrow() {
                return null;
            }

            @Override
            public int getParentLayoutIdForOverlappedListView() {
                return 0;
            }

            @Override
            public int getOverlappedListViewId() {
                return 0;
            }

            @Override
            public int getLayoutIdForOverlappedListView() {
                return 0;
            }

            @Override
            public void setOverlappedListViewLayout(ListView listView, int i, int i1, int i2) {

            }

            @Override
            public int getListItemLayoutIdForOverlappedListView() {
                return 0;
            }

            @Override
            public int getListItemTextViewId() {
                return 0;
            }

            @Override
            public int getListItemTailTextViewId() {
                return 0;
            }

            @Override
            public int getListItemImageViewId() {
                return 0;
            }

            @Override
            public int getListItemDividerId() {
                return 0;
            }

            @Override
            public void setOverlappedItemResource(NMapPOIitem nMapPOIitem, ImageView imageView) {

            }
        };
        mOverlayManager = new NMapOverlayManager(this,mMapView,mMapViewerResourceProvider);
    }


}





