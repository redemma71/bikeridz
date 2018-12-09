package com.chadcover.bikeridz.mapquest;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapquestRouteParser extends AsyncTask<String, Void, List<Maneuver>> {

    protected List<Maneuver> maneuvers = new ArrayList<>();

    public interface AsynResponse {
        void processFinish(List<Maneuver> maneuvers);
    }

    public AsynResponse delegate = null;

    public MapquestRouteParser(AsynResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<Maneuver> doInBackground(String ... strings) {

        // Log.i("String1", strings[0]);
        // Log.i("String2", strings[1]);

        // create an list of Mapquest route "maneuvers" asynchronously
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("www.mapquestapi.com")
                .appendPath("directions")
                .appendPath("v2")
                .appendPath("route")
                .appendQueryParameter("key","I9cVIOffUqMoMKhdvbhEUt6LWcpWfeML")
                .appendQueryParameter("from",strings[0])
                .appendQueryParameter("to",strings[1])
                .appendQueryParameter("routeType","bicycle");
        String mapquestUrlStr = builder.build().toString();

        try {
            URL mapquestUrl = new URL(mapquestUrlStr);

            // create connection
            HttpURLConnection myConnection = (HttpURLConnection) mapquestUrl.openConnection();
            myConnection.setRequestMethod("GET");
            myConnection.setRequestProperty("User-agent", "com-chadcover-restapiapp-v0.1");

            if (myConnection.getResponseCode() == 200) {
                // Log.d("MainActivity","myConnection.getResponseCode() == 200");
                // if successful call the input stream
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");

                // begin reading the Json response body
                JsonReader jsonReader = new JsonReader(responseBodyReader);

                try {
                    while (jsonReader.hasNext()) {
                        JsonToken nextToken = jsonReader.peek();
                        if (JsonToken.BEGIN_OBJECT.equals(nextToken)) {
                            jsonReader.beginObject();
                        } else if (JsonToken.END_ARRAY.equals(nextToken)) {
                            jsonReader.endArray();
                        } else if (JsonToken.BEGIN_ARRAY.equals(nextToken)) {
                            jsonReader.skipValue(); // the only array we need is "legs"
                        } else if (JsonToken.NAME.equals(nextToken)) {
                            String name = jsonReader.nextName();
                            if ("boundingBox".equals(name)
                                    || "computedWaypoints".equals(name)
                                    || "routeError".equals(name)
                                    ) {
                                jsonReader.skipValue();
                            }

                            if ("legs".equals(name)) {
                                jsonReader.beginArray();
                                try {
                                    while (jsonReader.hasNext()) {
                                        JsonToken legsToken = jsonReader.peek();
                                        if (JsonToken.BEGIN_OBJECT.equals(legsToken)) {
                                            jsonReader.beginObject();
                                        } else if (JsonToken.END_OBJECT.equals(legsToken)) {
                                            jsonReader.endObject();
                                        } else if (JsonToken.END_ARRAY.equals(legsToken)) {
                                            jsonReader.endArray();
                                        } else if (JsonToken.BEGIN_ARRAY.equals(legsToken)) {
                                            jsonReader.skipValue(); // can skip all arrays but "maneuvers"
                                        } else if (JsonToken.NAME.equals(legsToken)) {
                                            // look for the legs key
                                            String legsKey = jsonReader.nextName();
                                            if ("maneuvers".equals(legsKey)) {
                                                jsonReader.beginArray();
                                                while (jsonReader.hasNext()) {
                                                    JsonToken maneuversToken = jsonReader.peek();
                                                    if (JsonToken.BEGIN_OBJECT.equals(maneuversToken)) {
                                                        jsonReader.beginObject();
                                                    } else if (JsonToken.END_OBJECT.equals(maneuversToken)) {
                                                        jsonReader.endObject();
                                                    } else if (JsonToken.BEGIN_ARRAY.equals(maneuversToken)) {
                                                        jsonReader.beginArray();
                                                        // jsonReader.skipValue(); // we aren't parsing any arrays in maneuvers
                                                    } else if (JsonToken.END_ARRAY.equals(maneuversToken)) {
                                                        jsonReader.endArray();
                                                        // jsonReader.skipValue(); // dotting the t's, as it were
                                                    } else if (JsonToken.NUMBER.equals(maneuversToken)) {
                                                        //Log.i("key value", Double.toString(jsonReader.nextDouble()));
                                                    } else if (JsonToken.STRING.equals(maneuversToken)) {
                                                        //Log.i("key value", jsonReader.nextString());
                                                    } else if (JsonToken.NAME.equals(maneuversToken)) {
                                                        String maneuversKey = jsonReader.nextName();

                                                        // TODO: figure out why these are throwing errors
                                                        // suspect that array/object is not closed at
                                                        // appropriate level
                                                        if ("roadGradeStrategy".equals(maneuversKey) ||
                                                                "streets".equals(maneuversKey) ||
                                                                "startPoint".equals(maneuversKey) ||
                                                                "maneuverNotes".equals(maneuversKey) ||
                                                                "linkIds".equals(maneuversKey) ||
                                                                "signs".equals(maneuversKey)) {
                                                            jsonReader.skipValue();
                                                        } else {
                                                            maneuvers.add(readManeuver(maneuversKey, jsonReader));
                                                        }
                                                    }
                                                }
                                                jsonReader.endArray();
                                            }
                                        } else if (JsonToken.STRING.equals(legsToken)) {
                                            String value = jsonReader.nextString();
                                            //Log.i("STRING", value);
                                        } else if (JsonToken.NUMBER.equals(legsToken)) {
                                            Double value = jsonReader.nextDouble();
                                            //Log.i("NUMBER", Double.toString(value));
                                        } else if (JsonToken.BOOLEAN.equals(legsToken)) {
                                            boolean value = jsonReader.nextBoolean();
                                            //Log.i("BOOLEAN", Boolean.toString(value));
                                        } else if (JsonToken.NULL.equals(legsToken)) {
                                            //Log.i("NULL", "null");
                                        }
                                    }
                                    jsonReader.endObject();
                                } catch (IOException ioe) {
                                    Log.i("IOEXCEPTION", ioe.toString());
                                }
                                jsonReader.endArray();
                            }
                            //Log.d("MANEUVERS.SIZE()", Integer.toString(maneuvers.size()));
                        } else if (JsonToken.STRING.equals(nextToken)) {
                            String value = jsonReader.nextString();
                            //Log.i("STRING", value);
                        } else if (JsonToken.NUMBER.equals(nextToken)) {
                            Double value = jsonReader.nextDouble();
                            //Log.i("NUMBER", Double.toString(value));
                        } else if (JsonToken.BOOLEAN.equals(nextToken)) {
                            boolean value = jsonReader.nextBoolean();
                            //Log.i("BOOLEAN", Boolean.toString(value));
                        } else if (JsonToken.NULL.equals(nextToken)) {
                            //Log.i("NULL", "null");
                        }

                    }
                } catch (IOException ioe ){
                    Log.i("IOEXCEPTION", ioe.toString());
                }
                jsonReader.close();
                myConnection.disconnect();
            } else {
                Log.i("UNEXPECTEDRESPONSECODE", Integer.toString(myConnection.getResponseCode()));
            }

        } catch (MalformedURLException e) {
            Log.e("Exception:", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("Exception:", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("Exception:", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("Exception:", "Exception: " + e.getMessage());
        }

        return maneuvers;
    };

    @Override
    protected void onPostExecute(List<Maneuver> maneuvers) {
        //ListIterator<Maneuver> maneuversIter = maneuvers.listIterator();
        //while (maneuversIter.hasNext()) {
        //    Maneuver thisManeuver = maneuversIter.next();
        //    Log.i("MANEUVER-NARRATIVE", thisManeuver.getNarrative());
        // }
        delegate.processFinish(maneuvers);
    }


    protected Maneuver readManeuver(String initialKey, JsonReader reader) {
        Maneuver routeManeuver = new Maneuver();

        try {
            while (reader.hasNext()) {
                JsonToken maneuversToken = reader.peek();

                if (JsonToken.BEGIN_OBJECT.equals(maneuversToken)) {
                    // open the object
                    reader.beginObject();
                } else if (JsonToken.BEGIN_ARRAY.equals(maneuversToken)) {
                    reader.skipValue();
                } else if (JsonToken.END_ARRAY.equals(maneuversToken)) {
                    reader.endArray();
                } else if (JsonToken.END_OBJECT.equals(maneuversToken)) {
                    reader.endObject();
                }
                else if (JsonToken.STRING.equals(maneuversToken)) {
                    reader.skipValue(); // any meaningful string has been added to Maneuvers object already
                } else if (JsonToken.NUMBER.equals(maneuversToken)) {
                    // TODO: figure out why readManeuver starts at
                    // number token instead of name token = "distance"
                    // the following is a hack.
                    if ("distance".equals(initialKey)) {
                        Double maneuverDistance = reader.nextDouble();
                        //Log.d("DISTANCE", Double.toString(maneuverDistance));
                        routeManeuver.setDistance(maneuverDistance);
                        initialKey = "";
                    } else {
                        reader.skipValue(); // any meaningful number has already been added to Maneuvers object
                    }
                } else if (JsonToken.NAME.equals(maneuversToken)) {
                    String name = reader.nextName();
                    if (name.equals("narrative")) {
                        String maneuverNarrative = reader.nextString();
                        //Log.d("NARRATIVE", maneuverNarrative);
                        routeManeuver.setNarrative(maneuverNarrative);
                    } else if (name.equals("turnType")) {
                        int maneuverTurnType = reader.nextInt();
                        routeManeuver.setTurnType(maneuverTurnType);
                        //Log.d("TURNTYPE", Integer.toString(maneuverTurnType));
                    } else if (name.equals("formattedTime")) {
                        String maneuverFormattedTime = reader.nextString();
                        routeManeuver.setTime(maneuverFormattedTime);
                        //Log.d("FORMATTED_TIME", maneuverFormattedTime);
                    } else if (name.equals("iconUrl")) {
                        String maneuverIconUrl = reader.nextString();
                        routeManeuver.setIconUrl(maneuverIconUrl);
                        //Log.i("ICONURL", maneuverIconUrl);
                    } else if (name.equals("direction")) {
                        int maneuverDirection = reader.nextInt();
                        routeManeuver.setDirection(maneuverDirection);
                        //Log.i("DIRECTION", Integer.toString(maneuverDirection));
                    } else if (name.equals("startPoint") ||
                            name.equals("maneuverNotes") ||
                            name.equals("linkIds") ||
                            name.equals("signs") ||
                            name.equals("streets")) {
                        reader.skipValue();
                    }
                }
            }
            reader.endObject();
        } catch (MalformedURLException e) {
            Log.e("Exception:", "MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Log.e("Exception:", "ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Log.e("Exception:", "IOException: " + e.getMessage());
        } catch (Exception e) {
            Log.e("Exception:", "Exception: " + e.getMessage());
        }
        return routeManeuver;
    }

}
