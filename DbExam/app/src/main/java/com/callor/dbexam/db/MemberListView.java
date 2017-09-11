package com.callor.dbexam.db;

import android.content.ContentResolver;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.callor.dbexam.R;
import com.callor.dbexam.adapter.MemberViewAdapter;
import com.callor.dbexam.databinding.ActivityMemberListViewBinding;
import com.callor.dbexam.databinding.ContentMemberListViewBinding;

import java.util.ArrayList;
import java.util.List;

public class MemberListView extends AppCompatActivity {

    ActivityMemberListViewBinding mainBinding;
    ContentMemberListViewBinding contenBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_member_list_view);
        contenBinding = mainBinding.contentMain;
        setSupportActionBar(mainBinding.toolbar);

        Log.d("Member","Start");

        DBHelper dbHelper = new DBHelper(MemberListView.this);
        Cursor cursor = dbHelper.getListAll();
        cursor.moveToFirst();
        List<MemberVO> members = new ArrayList<>();

        while(cursor.moveToNext()) {

            MemberVO vo = new MemberVO();
            int intUserId = cursor.getColumnIndex(DBContract.DBColumn.USER_ID);
            String userId = cursor.getString(intUserId);
            vo.setUserId(userId);

            int intUseEmail = cursor.getColumnIndex(DBContract.DBColumn.USER_EMAIL);
            String userEmail = cursor.getString(intUseEmail);
            vo.setUserEmail(userEmail);

            int intUserPhone = cursor.getColumnIndex(DBContract.DBColumn.USER_PHONE);
            String userPhone = cursor.getString(intUserPhone);
            vo.setUserPhone(userPhone);

            members.add(vo);

            /*
            members.add(new MemberVO(
                        cursor.getString(cursor.getColumnIndex(DBContract.DBColumn.USER_ID)),
                        "",
                        cursor.getString(cursor.getColumnIndex(DBContract.DBColumn.USER_EMAIL)),
                        cursor.getString(cursor.getColumnIndex(DBContract.DBColumn.USER_PHONE))
            ));
            */
        }

        Log.d("VO",members.toString());
        MemberViewAdapter adapter = new MemberViewAdapter(members);
        contenBinding.memberList.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MemberListView.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contenBinding.memberList.setLayoutManager(linearLayoutManager);

    }
    class myCursor implements Cursor {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public int getPosition() {
            return 0;
        }

        @Override
        public boolean move(int i) {
            return false;
        }

        @Override
        public boolean moveToPosition(int i) {
            return false;
        }

        @Override
        public boolean moveToFirst() {
            return false;
        }

        @Override
        public boolean moveToLast() {
            return false;
        }

        @Override
        public boolean moveToNext() {
            return false;
        }

        @Override
        public boolean moveToPrevious() {
            return false;
        }

        @Override
        public boolean isFirst() {
            return false;
        }

        @Override
        public boolean isLast() {
            return false;
        }

        @Override
        public boolean isBeforeFirst() {
            return false;
        }

        @Override
        public boolean isAfterLast() {
            return false;
        }

        @Override
        public int getColumnIndex(String s) {
            return 0;
        }

        @Override
        public int getColumnIndexOrThrow(String s) throws IllegalArgumentException {
            return 0;
        }

        @Override
        public String getColumnName(int i) {
            return null;
        }

        @Override
        public String[] getColumnNames() {
            return new String[0];
        }

        @Override
        public int getColumnCount() {
            return 0;
        }

        @Override
        public byte[] getBlob(int i) {
            return new byte[0];
        }

        @Override
        public String getString(int i) {
            return null;
        }

        public String getString(String name) {
            return this.getString(getColumnIndex(name));
        }


        @Override
        public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {

        }

        @Override
        public short getShort(int i) {
            return 0;
        }

        @Override
        public int getInt(int i) {
            return 0;
        }

        @Override
        public long getLong(int i) {
            return 0;
        }

        @Override
        public float getFloat(int i) {
            return 0;
        }

        @Override
        public double getDouble(int i) {
            return 0;
        }

        @Override
        public int getType(int i) {
            return 0;
        }

        @Override
        public boolean isNull(int i) {
            return false;
        }

        @Override
        public void deactivate() {

        }

        @Override
        public boolean requery() {
            return false;
        }

        @Override
        public void close() {

        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public void registerContentObserver(ContentObserver contentObserver) {

        }

        @Override
        public void unregisterContentObserver(ContentObserver contentObserver) {

        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {

        }

        @Override
        public Uri getNotificationUri() {
            return null;
        }

        @Override
        public boolean getWantsAllOnMoveCalls() {
            return false;
        }

        @Override
        public void setExtras(Bundle bundle) {

        }

        @Override
        public Bundle getExtras() {
            return null;
        }

        @Override
        public Bundle respond(Bundle bundle) {
            return null;
        }
    }
}


