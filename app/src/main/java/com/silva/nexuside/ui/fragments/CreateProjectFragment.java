package com.silva.nexuside.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.transition.MaterialSharedAxis;
import com.silva.nexuside.resources.R;
import com.silva.nexuside.databinding.FragmentCreateProjectBinding;
import com.silva.nexuside.enums.APILevel;
import com.silva.nexuside.enums.ProjectTemplates;
import com.silva.nexuside.project.CreateProjectTask;
import com.silva.nexuside.project.Project;
import static com.silva.util.FileUtil.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;
import com.silva.nexuside.ui.activities.MainActivity;
import com.silva.nexuside.ui.base.BaseFragment;
import android.view.animation.AccelerateDecelerateInterpolator;

public class CreateProjectFragment extends BaseFragment {
    
    private FragmentCreateProjectBinding binding;
    
    private ProjectTemplates template;
    private String error;
    private int apiLevelPos = 0;
    
    private String TEMPLATE_PKG_NAME = "com.example.myapp";
    private String TEMPLATE_APP_NAME = "MyApp";
    private String TEMPLATE_LOCATION_DIR = getExternalStorageDir() + "/NexusIDEProjects/";
    
    private List<String> languages = Arrays.asList("Java", "Kotlin");
    private List<APILevel> apiList = APILevel.getAvailableList();
    private List<String> apiLevels;
    
    public CreateProjectFragment(ProjectTemplates template) {
    	this.template = template;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateProjectBinding.inflate(inflater);
        binding.etSaveLocationName.setText(TEMPLATE_LOCATION_DIR);
        init();
        handleInsetts(binding.getRoot());
        return binding.getRoot();
    }
    
    public void init() {
        
        apiLevels = new ArrayList<>();
        for(APILevel apiLevel : apiList) {
        	apiLevels.add(apiLevel.getDescription());
        }
        
    	ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, languages);
        binding.etLanguage.setAdapter(languageAdapter);
        binding.etLanguage.setThreshold(1);

        ArrayAdapter<String> apiLevelAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, apiLevels);
        binding.etMinSdk.setAdapter(apiLevelAdapter);
        binding.etMinSdk.setThreshold(1);
        
        binding.etApplicationName.addTextChangedListener(new TextWatcher() {
            @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                	//nothing
                }
            @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                	binding.etPackageName.setText(buildPkg(s));
                }
            @Override
                public void afterTextChanged(Editable s) {
                	//nothing
                }
        });
        
        binding.tilSaveLocationName.setEndIconOnClickListener(v -> {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            	Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivityForResult(Intent.createChooser(intent, "Choose directory"), 0);
            }
        });
        
        binding.etMinSdk.setOnItemClickListener((parent, view, position, id) -> {
            apiLevelPos = position;
        });
        
        binding.create.setOnClickListener(v -> createProject());
        binding.exit.setOnClickListener(v -> {
             MainActivity mainActivity = (MainActivity) getActivity();
             mainActivity.onBackPressed();
        });
        
        checkErrors();
    }
    
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    	binding.toolbar.setTitle(template.getTitleResId());
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
        this.binding = null;
    }
    
    @Override
    @Deprecated
    public void onActivityResult(int request, int result, Intent data) {
        super.onActivityResult(request, result, data);
        if(result == Activity.RESULT_OK) {
        	switch(request) {
                case 0:
                    Uri uri = data.getData();
                    File file = new File(uri.getPath());
                    String[] split = file.getPath().split(":");
                    binding.etSaveLocationName.setText(getExternalStorageDir() + "/" + split[1] + "/");
                break;
            }
        }
    }
    
    
    public String buildPkg(CharSequence s) {
    	return s.toString()
        .replaceAll("[^a-zA-Z0-9]+", ".")
        .replaceAll("([a-z])([A-Z])", "$1.$2")
        .replaceAll("(?!<[a-zA-Z0-9])\\.[^a-zA-Z]+", ".")
        .replaceAll("^[^a-zA-Z]*|[^a-zA-Z]*$", "")
        .toLowerCase();
    }
    
    public boolean checkErrors() {
    	binding.etApplicationName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO: Implement this method
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(null)) {
                	error = getString(R.string.wizard_app_name_is_null);
                    binding.tilApplicationName.setError(error);
                } else if(new File(binding.etSaveLocationName.getText().toString(), s.toString()).exists()) {
                	error = getString(R.string.wizard_app_name_exists);
                    binding.tilApplicationName.setError(error);
                } else {
                    binding.tilApplicationName.setErrorEnabled(false);
                    error = null;
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO: Implement this method
            }
        });
        binding.etPackageName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // TODO: Implement this method
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.equals(null)) {
                    error = getString(R.string.wizard_pkg_name_is_null);
                	binding.tilPackageName.setError(error);
                } else if(s.length() < 5) {
                    error = getString(R.string.wizard_pkg_name_is_very_short);
                    binding.tilPackageName.setError(error);
                } else if(s.toString().matches(".*[^a-zA-Z0-9.].*") || s.toString().substring(0, 1).matches(".*\\d.*") || s.toString().endsWith(".")) {
                    error = getString(R.string.wizard_pkg_name_incorrect_characters);
                    binding.tilPackageName.setError(error);
                } else {
                    error = null;
                    binding.tilPackageName.setErrorEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO: Implement this method
            }
        });
        if(error != null) {
        	return true;
        }
        return false;
    }
    
    public void createProject() {
    	Project project = new Project();
        project.setTemplate(template);
        project.setAppName(binding.etApplicationName.getText().toString());
        project.setPackageName(binding.etPackageName.getText().toString());
        project.setPath(new File(binding.etSaveLocationName.getText().toString(), project.getAppName()));
        project.setLanguage(binding.etLanguage.getText().toString());
        project.setMinSdk(apiList.get(apiLevelPos).getApiLevel());
        CreateProjectTask projectTask = new CreateProjectTask(getContext());
        
        projectTask.execute(project);
    }
    
}
